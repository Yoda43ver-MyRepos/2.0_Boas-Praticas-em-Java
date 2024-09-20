package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;

import java.io.IOException;

public class CadastrarAbrigoCommand implements Command {
    @Override
    public void execute() {
       try{
           ClientHttpConfiguration client =  new ClientHttpConfiguration();
           AbrigoService abrigo = new AbrigoService(client);
           abrigo.cadastrarAbrigo();
       }catch(IOException | InterruptedException e){
           System.out.println(e.getMessage());
       }
    }
}
