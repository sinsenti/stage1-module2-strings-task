package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String accessModifier = signatureString.substring(0, signatureString.indexOf(" "));
        boolean hasAccessModifier = false;
        String returnType = "";
        String methodName = "";
        List<MethodSignature.Argument> listArgs = new ArrayList<>();
        if (accessModifier.equals("public") || accessModifier.equals("protected") || accessModifier.equals("private")) {
            hasAccessModifier = true;
            String splitFirst = signatureString.split(" ", 2)[1];
            returnType = splitFirst.substring(0, splitFirst.indexOf(" "));
            String splitSecond = splitFirst.split(" ", 2)[1];
            methodName = splitSecond.substring(0, splitSecond.indexOf("("));
            String[] arg = signatureString.substring(signatureString.indexOf("(") + 1).replace(')',
                    ' ').trim().split(" ");
            for (int i = 0; i < arg.length - 1; i += 2) {
                if (arg[i + 1].indexOf(',') != -1)
                    arg[i + 1] = arg[i + 1].substring(0, arg[i + 1].indexOf(","));
                listArgs.add(new MethodSignature.Argument(arg[i], arg[i + 1]));
            }
        } else {
            returnType = accessModifier;
            String splitFirst = signatureString.split(" ", 2)[1];
            methodName = splitFirst.substring(0, splitFirst.indexOf("("));
            String[] arg = signatureString.substring(signatureString.indexOf("(") + 1).replace(')',
                    ' ').trim().split(" ");
            for (int i = 0; i < arg.length - 1; i += 2) {
                if (arg[i + 1].indexOf(',') != -1)
                    arg[i + 1] = arg[i + 1].substring(0, arg[i + 1].indexOf(","));
                listArgs.add(new MethodSignature.Argument(arg[i], arg[i + 1]));
            }
        }
        MethodSignature result = null;
        if (listArgs.size() > 0) {
            result = new MethodSignature(methodName, listArgs);
        } else {
            result = new MethodSignature(methodName);
        }
        if(hasAccessModifier) {
            result.setAccessModifier(accessModifier);
        }
        result.setReturnType(returnType);
        return result;

//        throw new UnsupportedOperationException("You should implement this method.");
    }
      


}








