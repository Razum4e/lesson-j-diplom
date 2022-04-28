import java.util.List;

public interface SearchEngine {             //описывание поискового движка
    List<PageEntry> search(String word);    //на запрос из слова отвечает списком элементов результата ответа
}
