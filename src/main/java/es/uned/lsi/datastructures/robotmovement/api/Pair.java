package es.uned.lsi.datastructures.robotmovement.api;

public class Pair<L, R> {

    private final L left;
    private final R right;

    public Pair(L left, R right) {
        assert left != null;
        assert right != null;
        this.left = left;
        this.right = right;
    }

    public L getLeft() { return this.left; }
    public R getRight() { return this.right; }

    @Override
    public int hashCode() {
        return this.left.hashCode() ^ this.right.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Pair)) {
            return false;
        }
        Pair<L, R> pairO = (Pair<L, R>) o;
        return pairO.getLeft().equals(this.left) &&
                pairO.getRight().equals(this.right);
    }
}
