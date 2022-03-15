package valuebean;

public class Note {
    private int noteId=-1;
    private String personName="";
    private String title="";
    private String content="";
    private String publishdate="";

    public Note(int noteId, String persionName, String title, String content, String publishdate) {
        this.noteId = noteId;
        this.personName = persionName;
        this.title = title;
        this.content = content;
        this.publishdate = publishdate;
    }

    public Note(){

    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }
}
