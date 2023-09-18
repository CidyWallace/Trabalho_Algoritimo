import java.util.Iterator;

public class DequeString implements Iterable<String> {
    private int n;
    private No Sentinela;
    public DequeString(){
        n = 0;
        Sentinela = new No();
        Sentinela.prox = Sentinela;
        Sentinela.ant = Sentinela;
    }
    private class No{
        private String dado;
        private No prox;
        private No ant;
    }
    public void push_front(String item){
        No tmp = new No();
        tmp.dado = item;

        tmp.ant = Sentinela;
        tmp.prox = Sentinela;

        Sentinela.prox = tmp;
        tmp.prox = Sentinela.prox;

        Sentinela.prox = tmp;
        tmp.prox.ant = tmp;
        ++n;
    }
    public void push_back(String item){
        No tmp = new No();
        tmp.dado = item;

        tmp.ant = Sentinela.ant;
        tmp.prox = Sentinela;

        Sentinela.ant = tmp;
        tmp.ant.prox = tmp;
        n++;
    }
    public String pop_front(){
        No tmp = Sentinela.prox;
        String meuDado = tmp.dado;

        tmp.ant.prox = tmp.prox;

        tmp.prox.ant = tmp.ant;

        --n;
        return meuDado;
    }
    public String pop_back(){
        No tmp = Sentinela.ant;
        String meuDado = tmp.dado;

        tmp.ant.prox = tmp.prox;

        tmp.prox.ant = tmp.ant;
        --n;
        return meuDado;
    }













    @Override
    public Iterator<String> iterator() {
        return null;
    }
}
