using PetFinder.Backend.Domain.Core;

namespace PetFinder.Backend.Domain;

public class Photo : Entity
{
    public static readonly Photo Empty = new();
    
    public byte[] Content { get; private set; }

    public ImageSize Size { get; private set; }

    private Photo() : this(Array.Empty<byte>(), ImageSize.Xs)
    {
    }
    
    public Photo(byte[] content, ImageSize size)
    {
        Content = content;
        Size = size;
    }
}