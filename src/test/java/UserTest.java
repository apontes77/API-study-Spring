import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Validator;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public class UserTest {

    private Validator validator;


    @Before("")
    public void setUp(){}

    @Test
    public void naoDeveAceitarNomeMuitoGrande(){}

    @Test
    public void naoDeveAceitarUsuarioSemEmail(){}

    @Test
    public void naoDeveAceitarUsuarioSemAniversario(){}

    @Test
    public void naoDeveAceitarUsuarioSemCPF(){}
}
