const mongoose = require('mongoose');

const listingSchema = new mongoose.Schema({
   title: { type: String, required: true },
   description: { type: String, required: true },
   price: { type: Number, required: true },
   location: { type: String, required: true },
   availability: [String],
   imageUrl: { type: String, required: false },
   createdAt: { type: Date, default: Date.now }
});

module.exports = mongoose.model('Listing', listingSchema);



