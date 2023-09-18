import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
    public No first(){
        if(Sentinela == Sentinela.prox) return  null;
        return Sentinela.prox;
    }
    //TODO verificar depois, senti que pode ta errado
    public boolean isEmpty(){
        return n == 0;
    }
    public int size(){
        return n;
    }
    public ListIterator<String> iterator(){
        return new DequeIterator();
    }
    public class DequeIterator implements ListIterator<String>{
        private No atual = Sentinela.prox;
        private int indice = 0;
        private No acessadoultimo = null;
        public boolean hasNext(){return indice < n;}
        public boolean hasPrevious(){return indice > 0;}
        public int previousIndex(){return indice - 1;}
        public int nextIndex(){return indice;}

        public String next(){
            if(!hasNext()) return null;

            String meuDado = atual.dado;
            acessadoultimo = atual;
            atual = atual.prox;
            indice++;
            return meuDado;
        }
        public String previous(){
            if(!hasPrevious()) return null;
            atual = atual.ant;

            String meuDado = atual.dado;
            acessadoultimo = atual;
            indice--;
            return meuDado;
        }

        public String get(){
            if(atual == null) throw new IllegalStateException();
            return atual.dado;
        }
        public void set(String x){
            if(acessadoultimo == null) throw new IllegalStateException();
            acessadoultimo.dado = x;
        }
        public void remove(){
            if(acessadoultimo == null)throw new IllegalStateException();
            acessadoultimo.ant.prox = acessadoultimo.prox;
            acessadoultimo.prox.ant = acessadoultimo.ant;
            --n;
            if(atual == acessadoultimo) atual = acessadoultimo.prox;
            else
                indice--;
            acessadoultimo = null;
        }
        public void add(String x){
            No tmp = new No();
            tmp.dado = x;

            tmp.prox = atual.prox;
            tmp.ant =atual;

            tmp.prox.ant = tmp;
            atual.prox = tmp;

            n++;

        }
        public String toString(){
            StringBuilder s = new StringBuilder();
            for (String item : this){
                s.append(item+" ");
            }
            return s.toString();
        }
    }
}
