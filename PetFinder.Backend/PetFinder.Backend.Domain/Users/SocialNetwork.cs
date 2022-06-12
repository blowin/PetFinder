namespace PetFinder.Backend.Domain.Users;

public class SocialNetwork
{
    public string Name { get; private set; } = string.Empty;
    public string Link { get; private set; } = string.Empty;

    private SocialNetwork(){}

    public SocialNetwork(string name, string link)
    {
        Name = name;
        Link = link;
    }
}