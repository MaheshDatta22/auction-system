-- Users Table
CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Auction Items Table
CREATE TABLE IF NOT EXISTS auction_items (
    item_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    base_price DECIMAL(19, 2) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    seller_id INTEGER NOT NULL,
    version BIGINT,
    FOREIGN KEY (seller_id) REFERENCES users(user_id)
);

-- Bids Table
CREATE TABLE IF NOT EXISTS bids (
    bid_id SERIAL PRIMARY KEY,
    bid_amount DECIMAL(19, 2) NOT NULL,
    bid_time TIMESTAMP NOT NULL,
    item_id INTEGER NOT NULL,
    bidder_id INTEGER NOT NULL,
    FOREIGN KEY (item_id) REFERENCES auction_items(item_id),
    FOREIGN KEY (bidder_id) REFERENCES users(user_id)
);

-- Winners Table
CREATE TABLE IF NOT EXISTS winners (
    winner_id SERIAL PRIMARY KEY,
    item_id INTEGER NOT NULL UNIQUE,
    user_id INTEGER NOT NULL,
    winning_amount DECIMAL(19, 2) NOT NULL,
    declared_time TIMESTAMP NOT NULL,
    FOREIGN KEY (item_id) REFERENCES auction_items(item_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
