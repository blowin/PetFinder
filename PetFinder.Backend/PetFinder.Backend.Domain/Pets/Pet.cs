namespace PetFinder.Backend.Domain.Pets;

public class Pet
{
    public PetType Type { get; private set; }
    public Photo MainPhoto { get; private set; }
    
    public string? Name { get; set; }
    public List<Photo>? AdditionalPhotos { get; set; }
    public byte? Age { get; set; }
    public bool? Sterilized { get; set; }
    public string[]? Vaccination { get; set; }
    public string[]? Diseases { get; set; }
    public string? Notes { get; set; }

    private Pet() : this(PetType.Cat, Photo.Empty)
    {
    }
    
    public Pet(PetType type, Photo mainPhoto)
    {
        Type = type;
        MainPhoto = mainPhoto;
    }
}