/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leonardo
 */
public class Item {
    private int chave;
    /*
    *Outros dados
    */
    
    public Item(int chave){
        this.chave = chave;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }
    
    public int compara(Item item){
        if(item.getChave()< this.chave)
            return 1;
        else
            return 0;
    }
}
