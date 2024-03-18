package mockito;

import br.inatel.c214.BuscaHorario;
import br.inatel.c214.Horarios;
import br.inatel.c214.HorariosService;
import junit.*;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class TesteBuscaHorario {

    @Mock
    private HorariosService service;
    private BuscaHorario buscaHorario;

    @Before
    public void setup(){
        
    }
    
}
