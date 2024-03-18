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
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class TesteBuscaHorario {

    @Mock
    private HorariosService service;
    private BuscaHorario buscaHorario;

    @Before
    public void setup(){
        buscaHorario = new BuscaHorario(service);
    }
    
    @Test
    public void testeBuscaHorarioChris(){
        Mockito.when(service.busca(55)).thenReturn(HorarioConst.CHRIS);

        Horarios chris = buscaHorario.buscaHorarios(55);
        //Faz assertion
        assertEquals("Chris Lima", chris.getNome());
        assertEquals("17:30", chris.getHorarioDeAtendimento());
        assertEquals("E", chris.getPeriodo());
        assertEquals("a", chris.getSala());
        assertEquals("a", chris.getPredio());
    }

    @Test
    public void TesteBuscaHorarioInvalido(){
        Mockito.when(service.busca(56)).thenReturn(HorarioConst.INEXISTENTE);

        Horarios inexistente = buscaHorario.buscaHorarios(56);
        //Faz assertion
        assertEquals("chris", inexistente.getNome());
        assertEquals("17:30", inexistente.getHorarioDeAtendimento());
        assertEquals("E", inexistente.getPeriodo());
        assertEquals("a", inexistente.getSala());
        assertEquals("a", inexistente.getPredio());
    }

    @Test
    public void testeBuscaHorarioValido(){

        Mockito.when(service.horarioExistente(10)).thenReturn(true);
        //Faz a busca de um horário válido
        boolean horarioValido = service.horarioExistente(10);

        assertTrue(horarioValido);
    }
}
