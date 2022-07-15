using Google.Protobuf.WellKnownTypes;

namespace PetFinder.Backend.Grpc.Extension;

public static class StringExt
{
    public static NullableString ToNullableString(this string? self)
    {
        return string.IsNullOrEmpty(self) ? 
            new NullableString { Null = NullValue.NullValue } : 
            new NullableString { Data = self };
    }
}