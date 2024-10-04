const express = require('express');
const router = express.Router();
const Listing = require('../models/Listing');

// Get all listings
router.get('/', async (req, res) => {
   try {
      const listings = await Listing.find();
      res.json(listings);
   } catch (err) {
      res.status(500).json({ message: err.message });
   }
});

// Create a new listing
router.post('/', async (req, res) => {
  console.log(req.body);
  const listing = new Listing({
      title: req.body.title,
      description: req.body.description,
      price: req.body.price,
      location: req.body.location,
      availability: req.body.availability,
      imageUrl: req.body.imageUrl
    });

  try {
    const newListing = await listing.save();
    res.status(201).json(newListing);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});


module.exports = router;
