namespace PetFinder.Backend.Domain.Posts;

public record PostDetail(Guid Id, string Title, string? Description, List<Photo> Photos);