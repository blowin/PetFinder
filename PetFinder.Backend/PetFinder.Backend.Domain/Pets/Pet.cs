namespace PetFinder.Backend.Domain.Pets;

public class Pet : Entity
{
    public PetType Type { get; private set; }
    
    public string? Name { get; set; }
    public byte? Age { get; set; }
    public bool? Sterilized { get; set; }
    public string[]? Vaccination { get; set; }
    public string[]? Diseases { get; set; }
    public string? Notes { get; set; }

    public Pet() : this(PetType.Cat)
    {
    }
    
    public Pet(PetType type)
    {
        Type = type;
    }
}