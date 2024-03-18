package br.inatel.c214;

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
}
