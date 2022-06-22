using System.Collections;

namespace PetFinder.Backend.Domain.Core;

public sealed class Page<T> : IEnumerable<T>
{
    public ICollection<T> Items { get; }

    public bool HasNext { get; }

    public int PageSize { get; }
    
    public Page(ICollection<T> items, int pageSize, bool hasNext)
    {
        ArgumentNullException.ThrowIfNull(items);

        if (pageSize < 0)
            throw new ArgumentOutOfRangeException("pageSize should not be negative");

        Items = items;
        HasNext = hasNext;
        PageSize = pageSize;
    }

    public IEnumerator<T> GetEnumerator() => Items.GetEnumerator();

    IEnumerator IEnumerable.GetEnumerator() => GetEnumerator();
}

public static class PageExt
{
    public static Page<T> ToPage<T>(this IQueryable<T> self, int pageNumber, int pageSize = Constants.PageSize)
        where T : Entity
    {
        var result = pageNumber == 1
            ? self.OrderBy(e => e.Id).Take(pageSize + 1).ToList()
            : self.OrderBy(e => e.Id).Skip((pageNumber - 1) * pageSize).Take(pageSize + 1).ToList();

        var hasNext = result.Count > pageSize;
        if(hasNext)
            result.RemoveAt(result.Count - 1);

        return new Page<T>(result, pageSize, hasNext);
    }

    public static Page<T> ToPage<T>(this IEnumerable<T> self, int pageNumber, int pageSize = Constants.PageSize)
        where T : Entity
    {
        var result = pageNumber == 1
            ? self.OrderBy(e => e.Id).Take(pageSize + 1).ToList()
            : self.OrderBy(e => e.Id).Skip((pageNumber - 1) * pageSize).Take(pageSize + 1).ToList();

        var hasNext = result.Count > pageSize;
        if(hasNext)
            result.RemoveAt(result.Count - 1);

        return new Page<T>(result, pageSize, hasNext);
    }
}