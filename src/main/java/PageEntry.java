public class PageEntry implements Comparable<PageEntry> {
    private final String pdfName;   //имя пдф файла
    private final int page;         //номер страницы
    private final int count;        //кол-во встречаемости слова на странице

    public PageEntry(String pdfName, int page, int count) {
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }

    @Override
    public int compareTo(PageEntry o) {
        return Integer.compare(o.count, this.count);
    }

    @Override
    public String toString() {
        return pdfName + " " + page + " " + count + "\n";
    }
}
