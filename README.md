# PetFinder

Server:

1. Grpc
2. Jwt 
3. Mongodb
4. ASP NET Core



Client(Android):

1. Grpc
2. Coroutines flow
3. Room
4. Dagger / Hilt
5. Firebase notification
6. Firebase analytics
7. Firebase crashlytics



Models:

```csharp
public record class SocialNetwork(string Name, string Link);

public record class User(Guid Id, string Login, string Password, string? Name, string? Surname, string? PhoneNumber, Photo? Photo, SocialNetwork[]? SocialNetworks);

public record class Token(Guid Id, Guid UserId, string DeviceId, string Token, string RefreshToken);

public enum PetType { Cat, Dog }

public record class Photo(Guid Id, byte[] Content);

public record class Address(Guid Id, string FullAdress, double Latitude, double Longitude);

public record class Pet(Guid Id, string? Name, PetType Type, Photo MainPhoto, Photo[]? AdditionalPhotos, byte? Age, bool? Sterilized, string[]? Vaccination, string[]? Diseases, string? Notes);

public record class SearchPost(Guid Id, string Name, string Description, Address Address, Pet[] Pets, Photo[]? Photos, Guid CreatedUserId, DateTime? CloseDate);

public record class DonationPost(Guid Id, string Name, string Description, Address? Address, Pet Pet, Guid CreatedUserId, DateTime? CloseDate);
```
