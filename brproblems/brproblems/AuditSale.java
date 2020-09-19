package brproblems;

import java.io.File;
import java.util.PriorityQueue;
import java.util.Scanner;

class AuditInfo {
	public long price;
	public long confidence;

	public AuditInfo(long p, long c) {
		this.price = p;
		this.confidence = c;
	}

	public long getPriceConfidence() {
		return price * confidence;
	}

	@Override
	public String toString() {
		return this.price + ", " + this.confidence;
	}
}

public class AuditSale {
	private int K, MMK;
	PriorityQueue<AuditInfo> KQueue;
	PriorityQueue<AuditInfo> MQueue;

	public AuditSale(int M, int K) {
		this.MMK = M - K;
		this.K = K;
		if (K > 0) {
			KQueue = new PriorityQueue<>(K, (left, right) -> {
				return Long.valueOf(left.price).compareTo(right.price);
			});
		}
		if (MMK > 0) {
			MQueue = new PriorityQueue<>(MMK, (left, right) -> {
				return Long.valueOf(left.getPriceConfidence()).compareTo(right.getPriceConfidence());
			});
		}
	}

	public void addAuditInfo(AuditInfo auditInfo) {
		if (K > 0) {
			if (KQueue.size() < K) {
				KQueue.add(auditInfo);
			} else {
				AuditInfo kTop = KQueue.peek();
				if (auditInfo.price > kTop.price) {
					kTop = KQueue.poll();
					KQueue.add(auditInfo);
					addOrPollMQueue(kTop);
				} else {
					addOrPollMQueue(auditInfo);
				}
			}
		} else {
			addOrPollMQueue(auditInfo);
		}
	}

	private void addOrPollMQueue(AuditInfo auditInfo) {
		if (MMK > 0) {
			if (MQueue.size() < MMK) {
				MQueue.add(auditInfo);
			} else {
				AuditInfo mTop = MQueue.peek();
				if (auditInfo.getPriceConfidence() > mTop.getPriceConfidence()) {
					MQueue.poll();
					MQueue.add(auditInfo);
				}
			}
		}
	}

	private void printInformation() {
		long expectedPrice = 0;
		while (K > 0 && KQueue.size() > 0)
			expectedPrice += (KQueue.poll().price * 100);

		while (MMK > 0 && MQueue.size() > 0)
			expectedPrice += MQueue.poll().getPriceConfidence();

		System.out.println(expectedPrice);
	}

	private static void evaluate(Scanner sc, int N, int M, int K) {

		AuditSale auditSale = new AuditSale(M, K);
		for (int i = 0; i < N; i++) {
			long P = sc.nextInt();
			long C = sc.nextInt();
			auditSale.addAuditInfo(new AuditInfo(P, C));
		}

		auditSale.printInformation();
	}

	public static void main(String[] args) {

		try {
			File file = new File("auditsale.txt");
			Scanner sc = new Scanner(file);
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();
			evaluate(sc, N, M, K);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
