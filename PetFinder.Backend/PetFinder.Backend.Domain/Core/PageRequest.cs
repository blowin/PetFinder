namespace PetFinder.Backend.Domain.Core;

public readonly struct PageRequest
{
    private readonly int _page = 1;

    public PageRequest()
    {
    }

    public int Page
    {
        get => _page;
        init
        {
            if(value <= 0)
                throw new ArgumentOutOfRangeException(nameof(value), "Value should be greater than 0");
            _page = value;
        }
    }

    public int PageSize { get; init; } = Constants.MaxPageSize;
}