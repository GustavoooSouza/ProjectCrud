/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjExemploCrud.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Usuário
 */
public class Conversao {

    //Converte String em Data    
    public static Date converterData(String data) {
        //criar é o formato de date; 
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            if (data == null || data.trim().equals("")) {
                return null;
            } else {
                Date date = fmt.parse(data);
                return date;
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao converter data!\n Erro: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    //Data  String
    public static String data2String(Date data) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = fmt.format(data);
        return dataFormatada;
    }

    //Recuperar a Data Atual do sistema
    public static Date dataAtual() {
        Date novaData = new Date(System.currentTimeMillis());
        return novaData;
    }
}
