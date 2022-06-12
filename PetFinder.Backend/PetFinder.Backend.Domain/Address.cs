﻿namespace PetFinder.Backend.Domain;

public class Address
{
    public string FullAddress { get; set; } = string.Empty;
    public double Latitude { get; set; }
    public double Longitude { get; set; }
}