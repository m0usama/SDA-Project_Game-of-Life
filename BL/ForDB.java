public interface ForDB {
    public String[][] loadState (String StateName);
    public void saveState();
    public void deleteState(String StateName);
    public int[][] viewState();
}
