package com.inova.project_manager_api.utils.codeGenerator;

public class CodeGenerator {

    public String generateCode(String clientName, boolean isForeign, int priority, int projectId){

        String code = "";

        code+= getClientName(clientName);

        code+=checkisForeign(isForeign);

        code+=checkPriority(priority);

        code+="/"+projectId;
        System.out.println(code);

        return code;


    }

    private String getClientName(String clientName) {

        StringBuilder initials = new StringBuilder();

        String[] words = clientName.split("\\s+");

        for (String word : words) {
            if (!word.isEmpty()) {
                initials.append(word.charAt(0));
            }
        }

        return initials.toString();

    }

    private  String checkPriority(int priority) {
        String code = "";
        if(priority == 1 ){
            code+="/H";
        } else if (priority == 2) {
            code+="/M";
        }else if (priority == 3){
            code+="/L";
        }else{
            code+= "/";
        }
        return code;
    }

    private  String checkisForeign(boolean isForeign) {
        String code = "";
        if(isForeign){
            code+="/F";
        }else{
            code+="/L";
        }
        return code;
    }
}
