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
    public static Page<T> ToPage<T>(this IQueryable<T> self, PageRequest pageRequest)
        where T : IEntity
    {
        var result = pageRequest.Page == 1
            ? self.OrderBy(e => e.Id).Take(pageRequest.PageSize + 1).ToList()
            : self.OrderBy(e => e.Id).Skip((pageRequest.Page - 1) * pageRequest.PageSize).Take(pageRequest.PageSize + 1).ToList();

        var hasNext = result.Count > pageRequest.PageSize;
        if(hasNext)
            result.RemoveAt(result.Count - 1);

        return new Page<T>(result, pageRequest.PageSize, hasNext);
    }

    public static Page<T> ToPage<T>(this IEnumerable<T> self, PageRequest pageRequest)
        where T : IEntity
    {
        var result = pageRequest.Page == 1
            ? self.OrderBy(e => e.Id).Take(pageRequest.PageSize + 1).ToList()
            : self.OrderBy(e => e.Id).Skip((pageRequest.Page - 1) * pageRequest.PageSize).Take(pageRequest.PageSize + 1).ToList();

        var hasNext = result.Count > pageRequest.PageSize;
        if(hasNext)
            result.RemoveAt(result.Count - 1);

        return new Page<T>(result, pageRequest.PageSize, hasNext);
    }
}