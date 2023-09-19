import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Deque;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class DequeCity implements Iterable<Cidade>{

    private int n; //Contador de elementos
    private No Sentinela;

    public DequeCity(){
        n = 0;
    }

    private class No{
        private Cidade dado;
        private String chave;
        private No prox;
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

        //ajusta a sentinela e o seguinte
        Sentinela.prox = tmp;
        tmp.prox.ant = tmp;
        ++n;
    }

    public void push_back(String key, Cidade item){
        //criar novo no e armazenar dados
        No tmp = new No();
        tmp.dado = item;
        tmp.chave = key;

        //definir anterior e proximo do novo no
        tmp.ant = Sentinela.ant;
        tmp.prox = tmp;

        //ajustar a sentinela e o anterior
        Sentinela.ant = tmp;
        tmp.ant.prox = tmp;
        n++;
    }

    public boolean contains(String key){
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Cidade get(String key){
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (No x = Sentinela.prox; x != Sentinela; x = x.prox){
            if(key.equals(x.chave))
                return x.dado;
        }
        return null;
    }

    public void delete(String key){
        if(key == null) throw new IllegalArgumentException("argument to delete() is null");
        delete(Sentinela.prox, key);
    }

    public void remove(No tmp){
        tmp.ant.prox = tmp.prox;
        tmp.prox.ant = tmp.ant;
        --n;
    }

    private void delete(No x, String key){
        if(x == Sentinela) return;
        if(key.equals(x.chave)){
            remove(x);
            return;
        }
        delete(x.prox, key);
    }

    public void put(String key, Cidade val){
        if(key == null) throw new IllegalArgumentException("firts argument to put() is null");
        if(val == null){
            delete(key);
            return;
        }
        for(No x = Sentinela.prox; x != Sentinela; x = x.prox){
            if(key.equals(x.chave)){
                x.dado = val;
                return;
            }
        }
        push_front(key, val);
    }

    public Cidade pop_front(){
        No tmp = Sentinela.prox;
        Cidade meuDado = tmp.dado;
        tmp.ant.prox = tmp.prox;
        tmp.prox.ant = tmp.ant;
        --n;
        return meuDado;
    }

    public Cidade pop_back(){
        No tmp = Sentinela.ant;
        Cidade meuDado = tmp.dado;
        tmp.ant.prox = tmp.prox;
        tmp.prox.ant = tmp.ant;
        --n;
        return meuDado;
    }

    public No first(){
        if(Sentinela == Sentinela.prox) return null;
        return Sentinela.prox;
    }

    public boolean isEmpty(){
        return n==0;
    }

    public int size(){
        return n;
    }

    @Override
    public ListIterator<Cidade> iterator() {
        return new DequeIterator();
    }

    public class DequeIterator implements ListIterator<Cidade> {
        private No atual= Sentinela.prox;
        private int indice =0;
        private No acessadoultimo= null;

        public boolean hasNext(){return indice< (n);}
        public boolean hasPrevious(){return indice>0;}
        public int previousIndex(){return indice -1;}
        public int nextIndex(){return indice;}

        public Cidade next(){
            if(!hasNext()) return null;

            Cidade meuDado= atual.dado;
            acessadoultimo = atual;
            atual = atual.prox;
            indice++;
            return meuDado;
        }

        public Cidade previous(){
            if(!hasPrevious())return null;
            atual = atual.ant;

            Cidade meuDado= atual.dado;
            acessadoultimo= atual;
            indice--;
            return meuDado;
        }
        public Cidade get(){
            if(atual==null) throw new IllegalArgumentException();
            return atual.dado;
        }
        public void set(Cidade x){
            if(acessadoultimo==null)throw new IllegalArgumentException();
            acessadoultimo.dado= x;

        }
        public void remove(){
            if(acessadoultimo==null) throw new IllegalArgumentException();
                acessadoultimo.ant.prox= acessadoultimo.prox;
                acessadoultimo.prox.ant= acessadoultimo.ant;
                --n;
            if(atual==acessadoultimo) atual= acessadoultimo.prox;
            else indice--;
                acessadoultimo = null;
        }
        public void add(Cidade x){
            //Inserir apos atual
            No tmp= new No();
            tmp.dado=x;
            tmp.prox=atual.prox;
            tmp.ant= atual;

            tmp.prox.ant= tmp;
            tmp.prox=tmp;
            n++;
        }
        public String toString(){
            StringBuilder s= new StringBuilder();
            for(Cidade item: DequeCity.this){
                s.append(item + " ");
            }
            return s.toString();
        }
        public Iterable<String> keys(){
            Deque<String> queue = new Deque<String>();
            for(No x= Sentinela.prox; x !=Sentinela; x = x.prox)
                queue.push_back(x.chave);
            return queue;
        }
        public static void mais(String[] args){
            if (args.length <2){
                System.out.println("\n\nUso: java DequeCity arquivo-1 arquivo-2\n\n");
                System.exit(0);
            }
            try {
                FileReader in1= new FileReader(args[0]);
                BufferedReader br = new BufferedReader(in1);
                int total= Integer.parseInt(br.readLine());

                int temperature=0;
                PrintStream StdOut = null;
                StdOut.println(" Total = "+total);
                DequeCity st= new DequeCity();

                for (int i=0; i<total;i++ ) {
                    String tmp = br.readLine();
                    StringTokenizer tk = new StringTokenizer(tmp);
                    String key = tk.nextToken();
                    temperature = Integer.parseInt(tk.nextToken());
                    Cidade myCity = new Cidade(key, temperature);
                    st.put(key, myCity);
                }
                br.close();
                in1.close();
                StdOut.println("____Testando___Procure afterword");
                StdOut.println(st.get("afterword"));
                StdOut.println("____Testando___Procure Feeney");
                StdOut.println(st.get("Feeney"));
                StdOut.println("_____Testando___Procure Fee");
                StdOut.println("Fee");

                in1= new FileReader(args[1]);
                br= new BufferedReader(in1);

                total= Integer.parseInt(br.readLine());
                for(int i =0; i<total; i++){
                    String tmp= br.readLine();
                    StringTokenizer tk= new StringTokenizer(tmp);
                    Cidade myCity = st.get(tk.nextToken());

                    if(myCity == null) {System.out.println("\n[FAILED] "+tmp+ " não foi encontrada.");}
                    else{
                        System.out.println("\n[OK] " + myCity.get_nome() + "foi encontrada. Temperatura lá é : "+ myCity.get_temp() +"F");}

                    br.close();
                    in1.close();
                }
            } catch (IOException e){

            }


        }

    }
}


