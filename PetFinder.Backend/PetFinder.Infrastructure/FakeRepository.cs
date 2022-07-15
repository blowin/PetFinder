using PetFinder.Backend.Domain;
using PetFinder.Backend.Domain.Core;

namespace PetFinder.Infrastructure;

public abstract class FakeRepository<T> : IRepository<T>
    where T : IEntity
{
    private readonly Lazy<Dictionary<Guid, T>> _items;

    protected Dictionary<Guid, T> Items => _items.Value;

    protected FakeRepository()
    {
        _items = new Lazy<Dictionary<Guid, T>>(() => GenerateItems().ToDictionary(e => e.Id));
    }

    public void Add(T entity)
    {
        Items.Add(entity.Id, entity);
    }

    public void Update(T entity)
    {
        Items[entity.Id] = entity;
    }

    public void Delete(T entity)
    {
        Items.Remove(entity.Id);
    }

    public T? GetById(Guid id) => Items.TryGetValue(id, out var res) ? res : default;

    public Page<T> GetPage(PageRequest pageRequest) => Items.Values.ToPage(pageRequest);

    protected abstract IEnumerable<T> GenerateItems();
}