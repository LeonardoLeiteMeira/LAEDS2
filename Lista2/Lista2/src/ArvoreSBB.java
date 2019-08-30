public class ArvoreSBB {

    private static class No {
        Item reg;
        No esq, dir;
        byte incE, incD;
    }
    
    private boolean primeiraFolha;
    private No raiz;
    private long nosVisitados;
    private int tamanhoArvore;
    private static final byte Horizontal = 0;
    private static final byte Vertical = 1;
    private boolean propSBB;

    public ArvoreSBB() {
        this.raiz = null;
        this.propSBB = true;
        this.tamanhoArvore = 0;
        this.nosVisitados = 0;
    }

    private No insere(Item reg, No pai, No filho, boolean filhoEsq) {
        if (filho == null) {
            filho = new No();
            filho.reg = reg;
            filho.incE = Vertical;
            filho.incD = Vertical;
            filho.esq = null;
            filho.dir = null;
            if (pai != null)
                if (filhoEsq)
                    pai.incE = Horizontal;
                else
                    pai.incD = Horizontal;
            this.propSBB = false;
        } else if (reg.compara(filho.reg) < 0) {
            filho.esq = insere(reg, filho, filho.esq, true);
            if (!this.propSBB)
                if (filho.incE == Horizontal) {
                    if (filho.esq.incE == Horizontal) {
                        filho = this.ee(filho); // @{\it trasforma\c{c}\~ao esquerda-esquerda}@
                        if (pai != null)
                            if (filhoEsq)
                                pai.incE = Horizontal;
                            else
                                pai.incD = Horizontal;
                    } else if (filho.esq.incD == Horizontal) {
                        filho = this.ed(filho); // @{\it trasforma\c{c}\~ao esquerda-direita}@
                        if (pai != null)
                            if (filhoEsq)
                                pai.incE = Horizontal;
                            else
                                pai.incD = Horizontal;
                    }
                } else
                    this.propSBB = true;
        } else if (reg.compara(filho.reg) > 0) {
            filho.dir = insere(reg, filho, filho.dir, false);
            if (!this.propSBB)
                if (filho.incD == Horizontal) {
                    if (filho.dir.incD == Horizontal) {
                        filho = this.dd(filho); // @{\it trasforma\c{c}\~ao direita-direita}@
                        if (pai != null)
                            if (filhoEsq)
                                pai.incE = Horizontal;
                            else
                                pai.incD = Horizontal;
                    } else if (filho.dir.incE == Horizontal) {
                        filho = this.de(filho); // @{\it trasforma\c{c}\~ao direita-esquerda}@
                        if (pai != null)
                            if (filhoEsq)
                                pai.incE = Horizontal;
                            else
                                pai.incD = Horizontal;
                    }
                } else
                    this.propSBB = true;
        } else {
            System.out.println("Erro: Registro ja existente");
            this.propSBB = true;
        }
        return filho;
    }

    private Item pesquisa(Item reg, No p) {
        if (p == null)
            return null; // @{\it Registro n\~ao econtrado}@
        else if (reg.compara(p.reg) < 0) {
            nosVisitados++;
            return pesquisa(reg, p.esq);
        } else if (reg.compara(p.reg) > 0) {
            nosVisitados++;
            return pesquisa(reg, p.dir);
        } else
            return p.reg;
    }

    public void pesquisa(Item reg) {
        Item itemTemp = pesquisa(reg, this.raiz);

        if (itemTemp != null) {
            System.out.println("O item achado foi " + itemTemp.getChave());
        } else {
            System.out.println("Item não encontrado");
        }
    }

    public long getNosVisitados() {
        return nosVisitados;
    }

    public int getTamanhoArvore() {
        retornaQuantidadeNiveis();
        return tamanhoArvore;
    }

    public void insere(Item reg) {
        this.raiz = insere(reg, null, this.raiz, true);
    }

    private No ee(No ap) {
        No ap1 = ap.esq;
        ap.esq = ap1.dir;
        ap1.dir = ap;
        ap1.incE = Vertical;
        ap.incE = Vertical;
        ap = ap1;
        return ap;
    }

    private No ed(No ap) {
        No ap1 = ap.esq;
        No ap2 = ap1.dir;
        ap1.incD = Vertical;
        ap.incE = Vertical;
        ap1.dir = ap2.esq;
        ap2.esq = ap1;
        ap.esq = ap2.dir;
        ap2.dir = ap;
        ap = ap2;
        return ap;
    }

    private No dd(No ap) {
        No ap1 = ap.dir;
        ap.dir = ap1.esq;
        ap1.esq = ap;
        ap1.incD = Vertical;
        ap.incD = Vertical;
        ap = ap1;
        return ap;
    }

    private No de(No ap) {
        No ap1 = ap.dir;
        No ap2 = ap1.esq;
        ap1.incE = Vertical;
        ap.incD = Vertical;
        ap1.esq = ap2.dir;
        ap2.dir = ap1;
        ap.dir = ap2.esq;
        ap2.esq = ap;
        ap = ap2;
        return ap;
    }

    private void calculaTamanho(No p, int nivel) {
        if (p == null) {
            return;
        }
        if (this.primeiraFolha) {
            if (this.tamanhoArvore < nivel) {
                this.tamanhoArvore = nivel;
            }
        }
        if (p.esq == null && p.dir == null) {
            if (this.primeiraFolha) {
                this.primeiraFolha = false;
            }
        }
        if (p.incE == Horizontal) {
            this.calculaTamanho(p.esq, nivel);
        } else {
            this.calculaTamanho(p.esq, nivel + 1);
        }
        if (p.incD == Horizontal) {
            this.calculaTamanho(p.dir, nivel);
        } else {
            this.calculaTamanho(p.dir, nivel + 1);
        }
    }

    private void retornaQuantidadeNiveis() {
        this.tamanhoArvore = 0;
        this.primeiraFolha = true;
        this.calculaTamanho(this.raiz, 1);
    }

}
