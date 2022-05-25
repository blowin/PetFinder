namespace PetFinder.Backend.Domain.Users;

public class User : Entity
{
    public string Login { get; private set; }
    public string Password { get; private set; }
    
    public string? Name { get; set; }
    public string? Surname { get; set; }
    public string? PhoneNumber { get; set; }
    public Photo? Photo { get; set; }
    public SocialNetwork[]? SocialNetworks { get; set; }

    private User() : this(string.Empty, string.Empty)
    {
    }
    
    public User(string login, string password)
    {
        Login = login;
        Password = password;
    }
}