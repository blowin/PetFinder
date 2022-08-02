using PetFinder.Backend.Domain.Core;

namespace PetFinder.Backend.Domain;

public class Photo : Entity
{
    public static readonly Photo Empty = new();
    
    public string Base64 { get; private set; }

    public ImageSize Size { get; private set; }

    private Photo() : this(string.Empty, ImageSize.Xs)
    {
    }
    
    public Photo(string base64, ImageSize size)
    {
        Base64 = base64;
        Size = size;
    }
}