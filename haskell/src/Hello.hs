module Hello (greet) where

greet :: String -> String
greet who = "Hello (Haskell), " ++ who ++ "!"
