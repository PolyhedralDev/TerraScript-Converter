package com.dfsek.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class MovedObjectInputStream extends ObjectInputStream {
    private final Map<String, String> nameMap;

    public MovedObjectInputStream(InputStream in, Map<String, String> nameMap) throws IOException {
        super(in);
        this.nameMap = nameMap;
    }

    @Override
    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        ObjectStreamClass result = super.readClassDescriptor();

        try {
            System.out.println("Resolving class " + result.getName());
            if(nameMap.containsKey(result.getName())) {
                String newClassName = nameMap.get(result.getName());
                // Test the class exists
                Class<?> localClass = Class.forName(newClassName);

                Field nameField = ObjectStreamClass.class.getDeclaredField("name");
                nameField.setAccessible(true);
                nameField.set(result, newClassName);

                ObjectStreamClass localClassDescriptor = ObjectStreamClass.lookup(localClass);
                Field suidField = ObjectStreamClass.class.getDeclaredField("suid");
                suidField.setAccessible(true);
                suidField.set(result, localClassDescriptor.getSerialVersionUID());
            }
            System.out.println("Resolved to " + result.getName());
        } catch(Exception e) {
            throw new RuntimeException(new IOException("Exception when trying to replace namespace", e));
        }


        return result;
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws ClassNotFoundException {
        String name = nameMap.getOrDefault(desc.getName(), desc.getName());
        return Class.forName(name);
    }
}