import java.io.*;
public class Item {
  private int chave;
  // @{\it outros componentes do registro}@

  public Item (int chave) { this.chave = chave; }
  
  public int compara (Item it) {
    Item item = (Item) it;
    if (this.chave < item.chave) return -1;
    else if (this.chave > item.chave) return 1;
    return 0;
  }
  
  public void setChave (Object chave) {
    Integer ch = (Integer) chave; this.chave = ch.intValue ();
  }
  
  public int getChave () { return this.chave; }
  
  public String toString () { return "" + this.chave; }
  
  public void gravaArq (RandomAccessFile arq) throws IOException {
    arq.writeInt (this.chave);
  }
  
  public void leArq (RandomAccessFile arq) throws IOException {
    this.chave = arq.readInt ();
  }
 
  public static int tamanho () { return 4; /* @{\it 4 bytes}@ */ }
}