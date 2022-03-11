import java.util.*;
import java.lang.Math;

// Minha solucao para o problema "Senha da Vo Zinha"
public class senha {

    static int n, m, k;

    // Funcao que retorna o minimo entre a e b
    static int min(double a, double b) {
        double toReturn;
        
        if(a < b)
            toReturn = a;
        else 
            toReturn = b;
        
        return (int) toReturn;
    }

    /*
        Funcao que busca os caracteres que devem preencher 
        as letras borradas na senha
    */
    public static List<Character> getChars(int p, List<String> palavras) {

        // Lista com a resposta, os caracteres que preencherao a senha
        List<Character> chars = new ArrayList<Character>();
        double MAX_RANGE = (double) (1000000000 + 7);
        
        char chr;
        // Percorrendo todas as palavras da lista
        for(int i = 0; i < m - 1; i++) {
            // Buscando o indice do caractere i (que ta borrado na senha) na i-esima palavra. 
            
            /*
                Basicamente, escolhendo um caractere na primeira palavra, temos  
                k * k * ... * k (m - 1 vezes) = k^(m - 1) possibilidades de escolhas de caracteres
                subsequentes na senha. Como a possibilidade de escolha podem estourar, boto o limite 
                MAX_RANGE.
            */
            int idxEscolha = min(Math.pow(k, m - (i + 1)), MAX_RANGE);

            /*
                Se escolhermos o primeiro caractere, sao k ^ (m - 1) possibilidades. Se for o 
                segundo, 2 * k ^ (m - 1), se for o terceiro, 3 * k ^ (m - 1). Entao basta fazer 
                p / idxEscolha para se obter o char na palavra atual. 
            */
            chr = palavras.get(i).charAt(p / idxEscolha);
            chars.add(chr);

            /* 
                Fazendo p = p % k ^ (m - 1), a escolha do proximo char nao e' influenciada 
                pela atual. Com isto, o processo se repete para todas m - 1 palavras seguintes.
            */
            p = p % idxEscolha;        
        }

        // Adicionando o ultimo caracetere a resposta
        chr = palavras.get(m - 1).charAt(p);
        chars.add(chr);
        
        return chars;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Lendo os valores
        n = input.nextInt();
        m = input.nextInt();
        k = input.nextInt();

        String senha;

        senha = input.next();

        List<String> palavras = new ArrayList<String>();

        // Lendo e ordenando as palavras da lista de substituicao 
        for(int i = 0; i < m; i++) {
            String word;
            word = input.next();
            
            char temp[] = word.toCharArray();
            Arrays.sort(temp);
            
            word = new String(temp);
            palavras.add(word);
        }

        int p;
        p = input.nextInt();

        // Obtendo a lista dos caracteres para substituicao
        List<Character> caracteres = getChars(p - 1, palavras);

        char[] ans = senha.toCharArray();
        int j = 0;
        // Substituindo os caracteres na senha
        for(int i = 0; i < n; i++) {
            if(ans[i] == '#') {
                ans[i] = caracteres.get(j);
                j++;
            }
        }
        
        System.out.println(ans);
    }
    
}