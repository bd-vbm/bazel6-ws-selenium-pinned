module Main where
import Hello (greet)

main :: IO ()
main = putStrLn (greet "World")
