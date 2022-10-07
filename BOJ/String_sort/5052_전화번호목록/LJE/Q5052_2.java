package String_Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class TrieNode{
    //자식 노드 맵
    private Map<Character, TrieNode> childNodes = new HashMap<>();
    //마지막 글자 확인 여부
    private boolean isLastChar;

    public Map<Character, TrieNode> getChildNodes() {
        return this.childNodes;
    }

    public boolean isLastChar() {
        return this.isLastChar;
    }

    public void setLastChar(boolean lastChar) {
        this.isLastChar = lastChar;
    }
}

class Trie{
    private TrieNode rootNode;

    public Trie() {
        rootNode = new TrieNode();
    }

    public void insert(String word){
        TrieNode thisNode = this.rootNode;

        for(int i=0;i<word.length();++i){
            thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }
        thisNode.setLastChar(true);
    }

    public boolean contains(String word){
        TrieNode thisNode = this.rootNode;

        for(int i=0;i<word.length();++i){
            char character = word.charAt(i);
            TrieNode node = thisNode.getChildNodes().get(character);
            if(node == null)
                return false;
            thisNode = node;
        }
        //만약 끝까지 탐색을 했는데, 마지막 노드가 끝이 아니였을 경우, 포함된 것
        if(thisNode.isLastChar()==false)
            return true;
        else
            return false;
    }
}

public class Q5052_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int Testcase = Integer.parseInt(br.readLine());
        for(int i=0;i<Testcase;++i){
            //전화번호 수
            int N = Integer.parseInt(br.readLine());
            String[] numbers = new String[N];
            for(int j=0;j<N;++j){
                numbers[j] = br.readLine();
            }
            System.out.println(Check(numbers));
        }
    }
    public static String Check(String[] numbers){
        Arrays.sort(numbers, Comparator.comparing(String::length).reversed());
        Trie tire = new Trie();
        for(int k=0;k< numbers.length;++k) {
            if(tire.contains(numbers[k])){
                return "NO";
            }
            tire.insert(numbers[k]);
        }
        return "YES";
    }
}

