using PetFinder.Backend.Domain.Core;

namespace PetFinder.Backend.Domain;

public class Entity : IEntity
{
    public Guid Id { get; set; }
}