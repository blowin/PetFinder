namespace PetFinder.Backend.Domain.Users;

public class Token : Entity
{
    public Guid UserId { get; private set; }
    public string DeviceId { get; private set; }
    public string TokenValue { get; private set; }
    public string RefreshToken { get; private set; }

    private Token() : this(Guid.Empty, string.Empty, string.Empty, string.Empty)
    {
    }
    
    public Token(Guid userId, string deviceId, string tokenValue, string refreshToken)
    {
        UserId = userId;
        DeviceId = deviceId;
        TokenValue = tokenValue;
        RefreshToken = refreshToken;
    }
}