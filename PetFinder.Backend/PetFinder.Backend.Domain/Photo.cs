namespace PetFinder.Backend.Domain;

public class Photo : Entity
{
    public static readonly Photo Empty = new();
    
    public byte[] Content { get; private set; }

    private Photo() : this(Array.Empty<byte>())
    {
    }
    
    public Photo(byte[] content)
    {
        Content = content;
    }
}