/**
 * A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.
 * A semiprime is a natural number that is the product of two (not necessarily distinct) prime numbers. The first few semiprimes are 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.
 * You are given two non-empty zero-indexed arrays P and Q, each consisting of M integers. These arrays represent queries about the number of semiprimes within specified ranges.
 * Query K requires you to find the number of semiprimes within the range (P[K], Q[K]), where 1 ≤ P[K] ≤ Q[K] ≤ N.
 * For example, consider an integer N = 26 and arrays P, Q such that:
 *     P[0] = 1    Q[0] = 26
 *     P[1] = 4    Q[1] = 10
 *     P[2] = 16   Q[2] = 20
 * The number of semiprimes within each of these ranges is as follows:
 * (1, 26) is 10,
 * (4, 10) is 4,
 * (16, 20) is 0.
 * Write a function:
 * class Solution { public int[] solution(int N, int[] P, int[] Q); }
 * that, given an integer N and two non-empty zero-indexed arrays P and Q consisting of M integers, returns an array consisting of M elements specifying the consecutive answers to all the queries.
 * For example, given an integer N = 26 and arrays P, Q such that:
 *     P[0] = 1    Q[0] = 26
 *     P[1] = 4    Q[1] = 10
 *     P[2] = 16   Q[2] = 20
 * the function should return the values [10, 4, 0], as explained above.
 * Assume that:
 * N is an integer within the range [1..50,000];
 * M is an integer within the range [1..30,000];
 * each element of arrays P, Q is an integer within the range [1..N];
 * P[i] ≤ Q[i].
 * Complexity:
 * expected worst-case time complexity is O(N*log(log(N))+M);
 * expected worst-case space complexity is O(N+M), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
1. Simple solution. Correctness = 100%, Performance = 40%, O(M * N ** (3/2)) or O(N * log(log(N)) + M * N)
class Solution {
    public int[] solution(int N, int[] P, int[] Q) {
        int length = P.length;
        int[] prime = sieve(N);
        boolean[] semiprime = semiprime(prime);
        int[] result = new int[length];
        for(int i=0;i<length;i++) {
            int primeNumber = countSemiprimes(P[i], Q[i], semiprime, N);  
            result[i] = primeNumber;
        }
        return result;
    }
    
    public int[] sieve(int N) {
        int[] prime = new int[N+1];
        for(int i=2; i<=(double)Math.sqrt(N); i++) {
            if(prime[i] == 0) {
                int k = i*i;
                while(k <= N) {
                    if(prime[k] == 0){
                        prime[k] = i;    
                    }                    
                    k += i;
                }               
            }
        }
        return prime;
    }
    
    public boolean[] semiprime(int[] prime) {
        boolean semiprime[] = new boolean[prime.length];
        for(int i=0;i<prime.length;i++) {
            if(prime[i] == 0) continue;
            int firstFactor = prime[i];
            if(prime[i/firstFactor] == 0) semiprime[i]=true;   
        }  
        return semiprime;
    }
    
    public int countSemiprimes(int P, int Q, boolean[] semiprime, int N) {
        int count = 0;
        if(P > Q || P > N || Q > N) return 0;
        for(int i=P==1?2:P;i<=Q;i++) {
            if(semiprime[i]) count++;
        }        
        return count;    
    }
}
2. Advanced solution, O(N * log(log(N)) + M)
class Solution {
    public int[] solution(int N, int[] P, int[] Q) {
        int length = P.length;
        int[] prime = sieve(N);
        int[] semiprime = semiprime(prime);
        int[] result = new int[length];
        int[] semiprimesAggreation = new int[N+1];
        
        for(int i=1;i<N+1;i++) {
            semiprimesAggreation[i] = semiprime[i];
            semiprimesAggreation[i] += semiprimesAggreation[i-1];    
        }
        
        for(int i=0;i<length;i++) { 
            result[i] = semiprimesAggreation[Q[i]] - semiprimesAggreation[P[i]] + semiprime[P[i]];
        }
        return result;
    }
    
    public int[] sieve(int N) {
        int[] prime = new int[N+1];
        for(int i=2; i<=(double)Math.sqrt(N); i++) {
            if(prime[i] == 0) {
                int k = i*i;
                while(k <= N) {
                    if(prime[k] == 0){
                        prime[k] = i;    
                    }                    
                    k += i;
                }               
            }
        }
        return prime;
    }
    
    public int[] semiprime(int[] prime) {
        int semiprime[] = new int[prime.length];
        for(int i=0;i<prime.length;i++) {
            if(prime[i] == 0) continue;
            int firstFactor = prime[i];
            if(prime[i/firstFactor] == 0) semiprime[i]=1;   
        }  
        return semiprime;
    }
}
/*Score: 100%*/