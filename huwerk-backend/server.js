const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
require('dotenv').config();

const app = express();

// Middleware
app.use(cors());
app.use(express.json());

// Connect to MongoDB
mongoose.connect(process.env.MONGO_URI, { useNewUrlParser: true, useUnifiedTopology: true })
   .then(() => console.log('Connected to MongoDB'))
   .catch(err => console.error('Failed to connect to MongoDB', err));

// Routes
const listingsRoute = require('./routes/listings');
app.use('/api/listings', listingsRoute);

// Start the server
const PORT = process.env.PORT || 5001;
app.listen(PORT, () => {
   console.log(`Server running on port ${PORT}`);
});
