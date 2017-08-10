public class Product {
  private String name;
  private double price;
  private double alcohol;
  private int volume;

  public Product(String name, double alcohol, double price, int volume) {
    this.name = name;                     
    this.alcohol = alcohol;
    this.price = price;
    this.volume = volume;
  }

  public String name() { return name; }
  public double alcohol() { return alcohol; }
  public double price() { return price; }
  public int volume() { return volume; }
  
  @Override
  public String toString() {
    return name + ", " +
      String.format("%.2f", alcohol) + "%, " +
      volume + " ml" + ", " +
      String.format("%.2f", price) + " SEK";
  }
}
