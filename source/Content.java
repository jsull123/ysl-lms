package source;

public abstract class Content {
    protected ContentType contentType;

    public ContentType getContentType(){
        return contentType;
    }

    public abstract boolean take();
}
