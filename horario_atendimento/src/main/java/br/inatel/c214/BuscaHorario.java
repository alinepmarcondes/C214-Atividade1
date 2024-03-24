package br.inatel.c214;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BuscaHorario {
    HorariosService horariosService;

    public BuscaHorario(HorariosService service){
        this.horariosService = service;
    }

    public Horarios buscaHorarios(int id){
        String horariosJson = horariosService.busca(id);

        JsonObject jsonObject = JsonParser.parseString(horariosJson).getAsJsonObject();

        return new Horarios(jsonObject.get("nomeDoProfessor").getAsString(),
                jsonObject.get("horarioDeAtendimento").getAsString(),
                jsonObject.get("periodo").getAsString(),
                jsonObject.get("sala").getAsString(),
                jsonObject.get("predio").getAsString());
    }

    public boolean verificaArrayListExistente(int id){
        boolean horarioExistente = horariosService.horarioExistente(id);

        if (horarioExistente){
            return true;
        }else{
            return false;
        }
    }

    public Integer parseStrInteger(String str){
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // Handle the exception, such as logging or returning a default value
            return 0;
        }
    }

    public boolean verificaSala(String sala){
        Integer salaInt = Integer.parseInt(sala);

        if(salaInt > 0 && salaInt <= 25){
            return true;
        }
        else{ 
            return false;
        }
    }

    public boolean verificaPredio(String predio){
        Integer predioInt = Integer.parseInt(predio);

        if((predioInt > 0 && predioInt < 5) || predioInt == 6){
            return true;
        }
        else{ 
            return false;
        }
    }

    public boolean verificaSalaPredio(String sala, String predio){
        Integer salaInt = Integer.parseInt(sala);
        Integer predioInt = Integer.parseInt(predio);

        if((((predioInt*5)-4) < salaInt) && (predioInt*5 > salaInt)){
            return true;
        }
        else{ 
            return false;
        }
    }

    public boolean verificaHoraAtendimento(String hora){

        String regex = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";

        Pattern pattern = Pattern.compile(regex);
    
        Matcher matcher = pattern.matcher(hora);

        return matcher.matches();
    }

    public boolean verificaTurno(String turno) {
        String[] turnos = {"Integral", "Noturno"};
        for (String t : turnos) {
            if (t.equalsIgnoreCase(turno)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaNome(String nome){

        String regex = "^[a-zA-Z\\s]+$";

        Pattern pattern = Pattern.compile(regex);
    
        Matcher matcher = pattern.matcher(nome);

        return matcher.matches();
    } 
}
