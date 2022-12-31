2. Написать класс TextContainer, который содержит в себе строку. С помощью механизма
   аннотаций указать 1) в какой файл должен сохраниться текст 2) метод, который выполнит
   сохранение. Написать класс Saver, который сохранит поле класса TextContainer в указанный
   файл.<br>
   @SaveTo(path=“c:\\file.txt”)<br>
   class Container {<br>
   String text = “…”;<br>
   @Saver<br>
   public void save(..) {…}<br>
   }<br>