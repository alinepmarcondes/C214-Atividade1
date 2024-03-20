package mockito;

import br.inatel.c214.BuscaHorario;
import br.inatel.c214.Horarios;
import br.inatel.c214.HorariosService;
import constante.HorariosConst;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        Mockito.when(service.busca(55)).thenReturn(HorariosConst.CHRIS);

        Horarios chris = buscaHorario.buscaHorarios(55);
        //Faz assertion
        assertEquals("Chris Lima", chris.getNome());
        assertEquals("17:30", chris.getHorarioDeAtendimento());
        assertEquals("integral", chris.getPeriodo());
        assertEquals("3", chris.getSala());
        assertEquals("1", chris.getPredio());
    }

    @Test
    public void TesteBuscaHorarioInvalido(){
        Mockito.when(service.busca(56)).thenReturn(HorariosConst.INEXISTENTE);

        Horarios inexistente = buscaHorario.buscaHorarios(56);
        //Faz assertion
        assertEquals("Inexistente", inexistente.getNome());
        assertEquals("Inexistente", inexistente.getHorarioDeAtendimento());
        assertEquals("Inexistente", inexistente.getPeriodo());
        assertEquals("Inexistente", inexistente.getSala());
        assertEquals("Inexistente", inexistente.getPredio());
    }

    @Test
    public void testeBuscaHorarioExistente(){

        Mockito.when(service.horarioExistente(10)).thenReturn(true);
        //Faz a busca de um horário válido
        boolean horarioValido = service.horarioExistente(10);

        assertTrue(horarioValido);
    }

    @Test
    public void testeBuscaHorarioInexistente(){

        Mockito.when(service.horarioExistente(66)).thenReturn(false);
        //Faz a busca de um horário válido
        boolean horarioValido = service.horarioExistente(66);

        assertFalse(horarioValido);
    }

    @Test    
    public void testeSalaValida(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.CHRIS);
        
        Horarios chris = buscaHorario.buscaHorarios(10);

        boolean salaValida = buscaHorario.verificaSala(chris.getSala());

        assertTrue(salaValida);
    }

    @Test    
    public void testeSalaInvalida(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.YVO);
        
        Horarios yvo = buscaHorario.buscaHorarios(10);

        boolean salaValida = buscaHorario.verificaSala(yvo.getSala());

        assertFalse(salaValida);
    }

    @Test    
    public void testePredioValido(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.CHRIS);
        
        Horarios chris = buscaHorario.buscaHorarios(10);

        boolean predioValido = buscaHorario.verificaPredio(chris.getPredio());

        assertTrue(predioValido);
    }

    @Test    
    public void testePredioInvalido(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.YVO);
        
        Horarios yvo = buscaHorario.buscaHorarios(10);

        boolean predioValido = buscaHorario.verificaPredio(yvo.getPredio());

        assertTrue(predioValido);
    }

    @Test
    public void testeSalaPredioValido(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.CHRIS);
        
        Horarios chris = buscaHorario.buscaHorarios(10);

        boolean salaValida = buscaHorario.verificaSalaPredio(chris.getSala(),chris.getPredio());

        assertTrue(salaValida);
    }

    @Test
    public void testeSalaPredioInvalido(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.RENZO);
        
        Horarios renzo = buscaHorario.buscaHorarios(10);

        boolean salaValida = buscaHorario.verificaSalaPredio(renzo.getSala(),renzo.getPredio());

        assertFalse(salaValida);
    }

    @Test
    public void testeHoraAtendimentoValido(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.RENZO);
        
        Horarios renzo = buscaHorario.buscaHorarios(10);

        boolean salaValida = buscaHorario.verificaHoraAtendimento(renzo.getHorarioDeAtendimento());

        assertTrue(salaValida);
    }

    @Test
    public void testeHoraAtendimentoInvalido(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.MARCELO);
        
        Horarios marcelinho = buscaHorario.buscaHorarios(10);

        boolean horaValida = buscaHorario.verificaHoraAtendimento(marcelinho.getHorarioDeAtendimento());

        assertFalse(horaValida);
    }
}
