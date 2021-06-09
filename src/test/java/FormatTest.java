import com.dfsek.converter.formats.ewg.EWGConverter;
import com.dfsek.converter.formats.iris.IrisConverter;
import com.dfsek.converter.formats.rwg.RWGConverter;
import com.dfsek.converter.formats.sponge.SpongeConverter;
import com.dfsek.converter.formats.tstructure.TStructureConverter;
import org.junit.Test;

public class FormatTest {
    @Test
    public void iris() {
        IrisConverter converter = new IrisConverter();
        System.out.println(converter.convert(FormatTest.class.getResourceAsStream("/irisStructure.iob")));
    }

    @Test
    public void irisLegacy() {
        IrisConverter converter = new IrisConverter();
        System.out.println(converter.convert(FormatTest.class.getResourceAsStream("/irisLegacyStructure.iob")));
    }

    @Test
    public void rwg() {
        RWGConverter converter = new RWGConverter();
        System.out.println(converter.convert(FormatTest.class.getResourceAsStream("/rwgStructure.rwgfast")));
    }

    @Test
    public void tStructure() {
        TStructureConverter converter = new TStructureConverter();
        System.out.println(converter.convert(FormatTest.class.getResourceAsStream("/tStructure.tstructure")));
    }

    @Test
    public void ewg() {
        EWGConverter converter = new EWGConverter();
        System.out.println(converter.convert(FormatTest.class.getResourceAsStream("/ewgStructure.EWG")));
    }

    @Test
    public void sponge() {
        SpongeConverter converter = new SpongeConverter();
        System.out.println(converter.convert(FormatTest.class.getResourceAsStream("/worldEditSponge.schem")));
    }
}
