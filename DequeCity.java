import java.util.ListIterator;

public class DequeCity implements Iterable<Cidade>{
    
    private int n; //Contador de elementos
    private No Sentinela; 

    public DequeCity(){
        n = 0;
        
    }

    private Class No{
        private Cidade dado;
        private String chave;
        private No proximo;
        private No ant;
    }

    public void push_front(String key, Cidade item){
        //Criar novo no e armazenar dados
        No tmp = new No();
        tmp.dado = item;
        tmp.chave = key;

        //definir anterior e proximo do novo no
        tmp.ant = Sentinela;
        tmp.prox = Sentinela.prox;
    }

    @Override
    public Iterator<Cidade> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }
    
}
