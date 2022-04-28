import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;
    /*поисковый движок работает если в директории есть файлы
    *типа .pdf
    *движок ищет в тексте ровно то слово в конструкторе,
    *которое было указано, без использования синонимов
    *и прочих приёмов нечётного поиска*/
public class BooleanSearchEngine implements SearchEngine {

    private final Map<String, List<PageEntry>> wordsAll = new HashMap<>();

    public BooleanSearchEngine(String pdfsDir) throws IOException {
        File file = new File(pdfsDir); //обозначили директорию
        File[] arrFiles = file.listFiles(); //поиск всех фалов в директории
        if (arrFiles == null)
            throw new IOException("\"" + pdfsDir + "\" не существует."); //директории нет
        for (File pdfName : arrFiles) { //обход по каждому файлу
            var doc = new PdfDocument(new PdfReader(pdfName));
            for (int page = 1; page <= doc.getNumberOfPages(); page++) { //обход по каждой странице файла
                var pdfPage = doc.getPage(page);
                var text = PdfTextExtractor.getTextFromPage(pdfPage);
                var words = text.split("\\P{IsAlphabetic}+"); //разделям каждое слово и сохраняем в массив
                Map<String, Integer> freqs = new HashMap<>(); //массив слова и его кол-ва на странице
                for (var word : words) {
                    if (word.isEmpty()) {
                        continue;
                    } //сохраняет ключем слово, а значением его кол-во, а если встречается еще, то каждый раз увеличивая
                    freqs.put(word.toLowerCase(), freqs.getOrDefault(word, 0) + 1);
                }
                for (Map.Entry<String, Integer> entry: freqs.entrySet()){ //обход каждого слова
                    List<PageEntry> pageEntryList = new ArrayList<>(); //лист изначальный для каждого слова
                    if (wordsAll.containsKey(entry.getKey())) // если слово в общей мапе существует, то
                        pageEntryList = wordsAll.get(entry.getKey()); //получаем лист имеющего слова и пересохр вместо изначального значения- листа
                    pageEntryList.add(new PageEntry(pdfName.getName(), page, entry.getValue())); //добавляем в значение-лист общей мапы
                    wordsAll.put(entry.getKey(), pageEntryList); //перезаписываем(если слово имеется) новым значением-листом
                }
            }
        }
    }

    @Override
    public List<PageEntry> search(String word) {
        if (wordsAll.containsKey(word.toLowerCase()))
            return wordsAll.get(word.toLowerCase());
        return Collections.emptyList();
    }
}
