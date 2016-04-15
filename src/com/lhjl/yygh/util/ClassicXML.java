package com.lhjl.yygh.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.lhjl.yygh.domain.DAkeshiListInfo;
import com.lhjl.yygh.domain.HuanzhejiluInfo;
import com.lhjl.yygh.domain.KeShiListInfo;
import com.lhjl.yygh.domain.PaiBanlistInfo;
import com.lhjl.yygh.domain.YiShengListInfo;
import com.lhjl.yygh.domain.YiYuanListInfo;

public class ClassicXML {
	/**
	 * @param 解析xml字符
	 *            �? 1。先加入dom4j.jar�?分析：根节点是：html ，子节点是head 、body ,
	 */

	public void readStringXml(String xml) {
		Document doc = null;
		try {
			// 1.读取并解析XML文档
			// SAXReader就是�?��管道，用�?��流的方式，把xml文件读出�?
			/*
			 * SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档
			 * Document document = reader.read(new File("User.hbm.xml"));
			 */
			// 下面的是通过解析xml字符串的
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节�?
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator iter = rootElt.elementIterator("CONST_HEAD"); // 获取根节点下的子节点head

			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("ERR_MSG"); // 拿到head节点下的子节点�?
				System.out.println("ERR_MSG:" + title);

				String code = recordEle.elementTextTrim("ERR_CODE");
				System.out.println("ERR_CODE:" + code);
				// Iterator iters = recordEle.elementIterator("script"); //
				// 获取子节点head下的子节点script

				// // 遍历Header节点下的Response节点
				// while (iters.hasNext()) {
				// Element itemEle = (Element) iters.next();
				// String username = itemEle.elementTextTrim("username"); //
				// 拿到head下的子节点script下的字节点username的�?
				// String password = itemEle.elementTextTrim("password");
				// System.out.println("username:" + username);
				// System.out.println("password:" + password);
				// }
			}

			Iterator iterss = rootElt.elementIterator("DATA_AREA"); // /获取根节点下的子节点body
			// 遍历body节点
			while (iterss.hasNext()) {
				Element recordEless = (Element) iterss.next();
				String result = recordEless.elementTextTrim("totalNumber"); // 拿到body节点下的子节点�?
				System.out.println("totalNumber:" + result);
				Iterator itersElIterator = recordEless
						.elementIterator("DATA_LIST");
				// 遍历Header节点下的Response节点
				// 遍历Header节点下的Response节点
				while (itersElIterator.hasNext()) {
					Element itemEle = (Element) itersElIterator.next();
					Iterator banlce = itemEle.elementIterator("DATE_ITEM");
					System.out.println("subID:" + "next");

					while (banlce.hasNext()) {
						Element itemEl = (Element) banlce.next();
						String banl = itemEl.elementTextTrim("departmentName");
						System.out.println("departmentName:" + banl);
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查询科室列表
	public static List<KeShiListInfo> readkeshiStringXmlOut(String xml) {
		Map map = new HashMap();
		Document doc = null;

		List<KeShiListInfo> info = new ArrayList<KeShiListInfo>();
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节�?
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator iter = rootElt.elementIterator("CONST_HEAD"); // 获取根节点下的子节点head
			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("ERR_MSG"); // 拿到head节点下的子节点�?
				map.put("ERR_MSG", title);
			}

			Iterator iterbody = rootElt.elementIterator("DATA_AREA"); // /获取根节点下的子节点body
			// 遍历body节点
			while (iterbody.hasNext()) {
				Element recordEless = (Element) iterbody.next();
				String result = recordEless.elementTextTrim("totalNumber"); // 拿到body节点下的子节点�?
				System.out.println("totalNumber:" + result);
				Iterator itersElIterator = recordEless
						.elementIterator("DATA_LIST"); // 获取子节点body下的子节�?
				// 遍历Header节点下的Response节点

				while (itersElIterator.hasNext()) {
					Element itemEle = (Element) itersElIterator.next();
					Iterator item = itemEle.elementIterator("DATE_ITEM");
					List allnews = itemEle.elements("DATA_ITEM");

					if (allnews.size() > 0) {
						for (int i = allnews.size(); i > 0; i--) {
							Element anews = (Element) allnews.get(i - 1);
							KeShiListInfo f = new KeShiListInfo();
							f.setDepartmentId(anews.elementText("departmentId"));
							f.setDepartmentName(anews
									.elementText("departmentName"));
							info.add(f);
						}
					}

				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// 大科室列表
	public static List<DAkeshiListInfo> readdakeshiStringXmlOut(String xml) {
		Map map = new HashMap();
		Document doc = null;

		List<DAkeshiListInfo> info = new ArrayList<DAkeshiListInfo>();
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节�?
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator iter = rootElt.elementIterator("CONST_HEAD"); // 获取根节点下的子节点head
			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("ERR_MSG"); // 拿到head节点下的子节点�?
				map.put("ERR_MSG", title);
			}

			Iterator iterbody = rootElt.elementIterator("DATA_AREA"); // /获取根节点下的子节点body
			// 遍历body节点
			while (iterbody.hasNext()) {
				Element recordEless = (Element) iterbody.next();
				String result = recordEless.elementTextTrim("totalNumber"); // 拿到body节点下的子节点�?
				System.out.println("totalNumber:" + result);
				Iterator itersElIterator = recordEless
						.elementIterator("DATA_LIST"); // 获取子节点body下的子节�?
				// 遍历Header节点下的Response节点

				while (itersElIterator.hasNext()) {
					Element itemEle = (Element) itersElIterator.next();
					Iterator item = itemEle.elementIterator("DATE_ITEM");
					List allnews = itemEle.elements("DATA_ITEM");

					if (allnews.size() > 0) {
						for (int i = allnews.size(); i > 0; i--) {
							Element anews = (Element) allnews.get(i - 1);
							DAkeshiListInfo f = new DAkeshiListInfo();
							f.setMainDepartmentId(anews
									.elementText("MainDepartmentId"));
							f.setMainDepartmentName(anews
									.elementText("MainDepartmentName"));
							info.add(f);
						}
					}

				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// 查询院区列表
	public static List<YiYuanListInfo> readyuanquStringXmlOut(String xml) {
		Map map = new HashMap();
		Document doc = null;

		List<YiYuanListInfo> info = new ArrayList<YiYuanListInfo>();
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节�?
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator iter = rootElt.elementIterator("CONST_HEAD"); // 获取根节点下的子节点head
			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("ERR_MSG"); // 拿到head节点下的子节点�?
				map.put("ERR_MSG", title);
			}

			Iterator iterbody = rootElt.elementIterator("DATA_AREA"); // /获取根节点下的子节点body
			// 遍历body节点
			while (iterbody.hasNext()) {
				Element recordEless = (Element) iterbody.next();
				String result = recordEless.elementTextTrim("totalNumber"); // 拿到body节点下的子节点�?
				System.out.println("totalNumber:" + result);
				Iterator itersElIterator = recordEless
						.elementIterator("DATA_LIST"); // 获取子节点body下的子节�?
				// 遍历Header节点下的Response节点

				while (itersElIterator.hasNext()) {
					Element itemEle = (Element) itersElIterator.next();
					Iterator item = itemEle.elementIterator("DATE_ITEM");
					List allnews = itemEle.elements("DATA_ITEM");

					if (allnews.size() > 0) {
						for (int i = allnews.size(); i > 0; i--) {
							Element anews = (Element) allnews.get(i - 1);
							YiYuanListInfo f = new YiYuanListInfo();
							f.setHospital(anews.elementText("hospital"));
							f.setHospitalName(anews.elementText("hospitalName"));
							f.setHospital_addr(anews.elementText("hospital_addr"));
							f.setHospital_dengji(anews.elementText("hospital_dengji"));
							f.setHospital_tel(anews.elementText("hospital_tel"));
							info.add(f);
						}
					}

				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// 医生列表
	public static List<YiShengListInfo> readyishengStringXmlOut(String xml) {
		Map map = new HashMap();
		Document doc = null;

		List<YiShengListInfo> info = new ArrayList<YiShengListInfo>();
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节�?
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator iter = rootElt.elementIterator("CONST_HEAD"); // 获取根节点下的子节点head
			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("ERR_MSG"); // 拿到head节点下的子节点�?
				map.put("ERR_MSG", title);
			}

			Iterator iterbody = rootElt.elementIterator("DATA_AREA"); // /获取根节点下的子节点body
			// 遍历body节点
			while (iterbody.hasNext()) {
				Element recordEless = (Element) iterbody.next();
				String result = recordEless.elementTextTrim("totalNumber"); // 拿到body节点下的子节点�?
				System.out.println("totalNumber:" + result);
				Iterator itersElIterator = recordEless
						.elementIterator("DATA_LIST"); // 获取子节点body下的子节�?
				// 遍历Header节点下的Response节点

				while (itersElIterator.hasNext()) {
					Element itemEle = (Element) itersElIterator.next();
					Iterator item = itemEle.elementIterator("DATE_ITEM");
					List allnews = itemEle.elements("DATA_ITEM");

					if (allnews.size() > 0) {
						for (int i = allnews.size(); i > 0; i--) {
							Element anews = (Element) allnews.get(i - 1);
							YiShengListInfo f = new YiShengListInfo();
							f.setDoctorDetail(anews.elementText("doctorDetail"));
							f.setDoctorId(anews.elementText("doctorId"));
							f.setDoctorName(anews.elementText("doctorName"));
							f.setFee(anews.elementText("Fee"));
							f.setSessionId(anews.elementText("SessionId"));
							f.setSessionType(anews.elementText("SessionType"));
							info.add(f);
						}
					}

				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// 锁号交易
	public static Map readStringSHJYXml(String xml) {
		Map map = new HashMap<String, String>();
		Document doc = null;
		String ERR_MSG = "", ERR_CODE = "";
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节�?
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator iter = rootElt.elementIterator("CONST_HEAD"); // 获取根节点下的子节点head
			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				ERR_MSG = recordEle.elementTextTrim("ERR_MSG"); // 拿到head节点下的子节点�?
				ERR_CODE = recordEle.elementTextTrim("ERR_CODE");
				map.put("ERR_MSG", ERR_MSG);
				map.put("ERR_CODE", ERR_CODE);
			}
			if (ERR_CODE.equals("00")) {
				Iterator iterbody = rootElt.elementIterator("DATA_AREA"); // /获取根节点下的子节点body
				// 遍历body节点
				while (iterbody.hasNext()) {
					Element recordEless = (Element) iterbody.next();
					String lockQueueNo = recordEless
							.elementTextTrim("lockQueueNo"); // 锁定的队列号
					String orgHISTradeNo = recordEless
							.elementTextTrim("orgHISTradeNo");// 交易流水号
					map.put("lockQueueNo", lockQueueNo);
					map.put("orgHISTradeNo", orgHISTradeNo);

				}

			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// 预约挂号交易
	public static Map readStringYYGHXml(String xml) {
		Map map = new HashMap<String, String>();
		Document doc = null;
		String ERR_MSG = "", ERR_CODE = "";
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节�?
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator iter = rootElt.elementIterator("CONST_HEAD"); // 获取根节点下的子节点head
			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				ERR_MSG = recordEle.elementTextTrim("ERR_MSG"); // 拿到head节点下的子节点�?
				ERR_CODE = recordEle.elementTextTrim("ERR_CODE");
				map.put("ERR_MSG", ERR_MSG);
				map.put("ERR_CODE", ERR_CODE);
			}
			if (ERR_CODE.equals("00")) {
				Iterator iterbody = rootElt.elementIterator("DATA_AREA"); // /获取根节点下的子节点body
				// 遍历body节点
				while (iterbody.hasNext()) {
					Element recordEless = (Element) iterbody.next();
					String scheduleItemCode = recordEless
							.elementTextTrim("scheduleItemCode"); // 门诊排班项代码
					String seqCode = recordEless.elementTextTrim("seqCode");// 挂号序号
					String orderCode = recordEless.elementTextTrim("orderCode");// 预约单号
					String admitTime = recordEless.elementTextTrim("admitTime");// 就诊时间
					String admitRange = recordEless
							.elementTextTrim("admitRange");// 时间段
					String regFee = recordEless.elementTextTrim("regFee");// 挂号费
					String admitAddress = recordEless
							.elementTextTrim("admitAddress");// 就诊地点
					String orderContent = recordEless
							.elementTextTrim("orderContent");// 预约单内容
					map.put("scheduleItemCode", scheduleItemCode);
					map.put("seqCode", seqCode);
					map.put("orderCode", orderCode);
					map.put("admitTime", admitTime);
					map.put("admitRange", admitRange);
					map.put("regFee", regFee);
					map.put("admitAddress", admitAddress);
					map.put("orderContent", orderContent);
				}

			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// 患者预约查询
	public static List<HuanzhejiluInfo> readhuanzheStringXmlOut(String xml) {
		Map map = new HashMap();
		Document doc = null;

		List<HuanzhejiluInfo> info = new ArrayList<HuanzhejiluInfo>();
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节�?
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator iter = rootElt.elementIterator("CONST_HEAD"); // 获取根节点下的子节点head
			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("ERR_MSG"); // 拿到head节点下的子节点�?
				map.put("ERR_MSG", title);
			}

			Iterator iterbody = rootElt.elementIterator("DATA_AREA"); // /获取根节点下的子节点body
			// 遍历body节点
			while (iterbody.hasNext()) {
				Element recordEless = (Element) iterbody.next();
				String result = recordEless.elementTextTrim("totalNumber"); // 拿到body节点下的子节点�?
				System.out.println("totalNumber:" + result);
				Iterator itersElIterator = recordEless
						.elementIterator("DATA_LIST"); // 获取子节点body下的子节�?
				// 遍历Header节点下的Response节点

				while (itersElIterator.hasNext()) {
					Element itemEle = (Element) itersElIterator.next();
					Iterator item = itemEle.elementIterator("DATE_ITEM");
					List allnews = itemEle.elements("DATA_ITEM");

					if (allnews.size() > 0) {
						for (int i = allnews.size(); i > 0; i--) {
							Element anews = (Element) allnews.get(i - 1);
							HuanzhejiluInfo f = new HuanzhejiluInfo();
							f.setApplicant(anews.elementText("applicant"));
							f.setApplyDate(anews.elementText("applyDate"));
							f.setDoctorName(anews.elementText("doctorName"));
							f.setBindCardNo(anews.elementText("bindCardNo"));
							f.setCancelFlag(anews.elementText("cancelFlag"));
							f.setContactTel(anews.elementText("contactTel"));
							f.setDepartmentItem(anews
									.elementText("departmentItem"));
							f.setMedicalAccountNo(anews
									.elementText("medicalAccountNo"));
							f.setOrderCode(anews.elementText("orderCode"));
							f.setOrderContent(anews.elementText("orderContent"));
							f.setOrderDate(anews.elementText("orderDate"));
							f.setOrderState(anews.elementText("orderState"));
							f.setPaymentState(anews.elementText("paymentState"));
							f.setRegistryFee(anews.elementText("registryFee"));
							f.setScheduleItemCode(anews
									.elementText("scheduleItemCode"));
							f.setSerialNo(anews.elementText("serialNo"));
							f.setTelephone(anews.elementText("telephone"));
							f.setHospitalName(anews.elementText("hospitalName"));
							info.add(f);
						}
					}

				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// 退号交易
	public static Map readStringTHJYXml(String xml) {
		Map map = new HashMap<String, String>();
		Document doc = null;
		String ERR_MSG = "", ERR_CODE = "";
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节�?
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator iter = rootElt.elementIterator("CONST_HEAD"); // 获取根节点下的子节点head
			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				ERR_MSG = recordEle.elementTextTrim("ERR_MSG"); // 拿到head节点下的子节点�?
				ERR_CODE = recordEle.elementTextTrim("ERR_CODE");
				map.put("ERR_MSG", ERR_MSG);
				map.put("ERR_CODE", ERR_CODE);
			}
			if (ERR_CODE.equals("00")) {
				Iterator iterbody = rootElt.elementIterator("DATA_AREA"); // /获取根节点下的子节点body
				// 遍历body节点
				while (iterbody.hasNext()) {
					Element recordEless = (Element) iterbody.next();
					String orderCode = recordEless.elementTextTrim("orderCode");// 预约单号
					String orgHISTradeNo = recordEless
							.elementTextTrim("orgHISTradeNo");// 就诊时间
					map.put("orderCode", orderCode);
					map.put("orgHISTradeNo", orgHISTradeNo);
				}

			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// 医生排班查询
	public static List<PaiBanlistInfo> readpaibanStringXmlOut(String xml) {
		Map map = new HashMap();
		Document doc = null;

		List<PaiBanlistInfo> info = new ArrayList<PaiBanlistInfo>();
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节�?
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator iter = rootElt.elementIterator("CONST_HEAD"); // 获取根节点下的子节点head
			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("ERR_MSG"); // 拿到head节点下的子节点�?
				map.put("ERR_MSG", title);
			}

			Iterator iterbody = rootElt.elementIterator("DATA_AREA"); // /获取根节点下的子节点body
			// 遍历body节点
			while (iterbody.hasNext()) {
				Element recordEless = (Element) iterbody.next();
				String result = recordEless.elementTextTrim("totalNumber"); // 拿到body节点下的子节点�?
				System.out.println("totalNumber:" + result);
				Iterator itersElIterator = recordEless
						.elementIterator("DATA_LIST"); // 获取子节点body下的子节�?
				// 遍历Header节点下的Response节点

				while (itersElIterator.hasNext()) {
					Element itemEle = (Element) itersElIterator.next();
					Iterator item = itemEle.elementIterator("DATE_ITEM");
					List allnews = itemEle.elements("DATA_ITEM");

					if (allnews.size() > 0) {
						for (int i = allnews.size(); i > 0; i--) {
							Element anews = (Element) allnews.get(i - 1);
							PaiBanlistInfo f = new PaiBanlistInfo();
							f.setAdmitSeg(anews.elementText("admitSeg"));
							f.setAdmitTime(anews.elementText("admitTime"));
							f.setAvailableNum(anews.elementText("availableNum"));
							f.setDepartmentId(anews.elementText("departmentId"));
							f.setDepartmentName(anews
									.elementText("departmentName"));
							f.setDoctorId(anews.elementText("doctorId"));
							f.setDoctorName(anews.elementText("doctorName"));
							f.setDoctorTitle(anews.elementText("doctorTitle"));
							f.setDoctorTitleCode(anews
									.elementText("doctorTitleCode"));
							f.setRoomCode(anews.elementText("roomCode"));
							f.setRoomName(anews.elementText("roomName"));
							f.setScheduleItemCode(anews
									.elementText("scheduleItemCode"));
							f.setTotalFee(anews.elementText("totalFee"));
							info.add(f);
						}
					}

				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// 绑定银行卡交易
	public static Map readbindStringXml(String xml) {
		Map map = new HashMap<String, String>();
		Document doc = null;
		String ERR_MSG = "", ERR_CODE = "";
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节�?
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator iter = rootElt.elementIterator("CONST_HEAD"); // 获取根节点下的子节点head
			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				ERR_MSG = recordEle.elementTextTrim("ERR_MSG"); // 拿到head节点下的子节点�?
				ERR_CODE = recordEle.elementTextTrim("ERR_CODE");
				map.put("ERR_MSG", ERR_MSG);
				map.put("ERR_CODE", ERR_CODE);
			}
			if (ERR_CODE.equals("00")) {
				Iterator iterbody = rootElt.elementIterator("DATA_AREA"); // /获取根节点下的子节点body
				// 遍历body节点
				while (iterbody.hasNext()) {
					Element recordEless = (Element) iterbody.next();
					String customerName = recordEless
							.elementTextTrim("customerName");// 预约单号
					String identityType = recordEless
							.elementTextTrim("identityType");// 就诊时间
					String identityNo = recordEless
							.elementTextTrim("identityNo");
					String bindCardNo = recordEless
							.elementTextTrim("bindCardNo");
					String telNo = recordEless.elementTextTrim("telNo");
					String transNumber = recordEless
							.elementTextTrim("transNumber");
					map.put("customerName", customerName);
					map.put("identityType", identityType);
					map.put("identityNo", identityNo);
					map.put("bindCardNo", bindCardNo);
					map.put("telNo", telNo);
					map.put("transNumber", transNumber);
				}

			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// 取消绑定银行卡交易
	public static Map readdelbindStringXml(String xml) {
		Map map = new HashMap<String, String>();
		Document doc = null;
		String ERR_MSG = "", ERR_CODE = "";
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节�?
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator iter = rootElt.elementIterator("CONST_HEAD"); // 获取根节点下的子节点head
			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				ERR_MSG = recordEle.elementTextTrim("ERR_MSG"); // 拿到head节点下的子节点�?
				ERR_CODE = recordEle.elementTextTrim("ERR_CODE");
				map.put("ERR_MSG", ERR_MSG);
				map.put("ERR_CODE", ERR_CODE);
			}
			if (ERR_CODE.equals("00")) {
				Iterator iterbody = rootElt.elementIterator("DATA_AREA"); // /获取根节点下的子节点body
				// 遍历body节点
				while (iterbody.hasNext()) {
					Element recordEless = (Element) iterbody.next();
					String customerName = recordEless
							.elementTextTrim("customerName");// 预约单号
					String identityType = recordEless
							.elementTextTrim("identityType");// 就诊时间
					String identityNo = recordEless
							.elementTextTrim("identityNo");
					String bindCardNo = recordEless
							.elementTextTrim("bindCardNo");
					String medicalAccountNo = recordEless
							.elementTextTrim("medicalAccountNo");
					String transNumber = recordEless
							.elementTextTrim("transNumber");
					map.put("customerName", customerName);
					map.put("identityType", identityType);
					map.put("identityNo", identityNo);
					map.put("bindCardNo", bindCardNo);
					map.put("medicalAccountNo", medicalAccountNo);
					map.put("transNumber", transNumber);
				}

			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// 账户概览
	public static Map readzhglbindStringXml(String xml) {
		Map map = new HashMap<String, String>();
		Document doc = null;
		String ERR_MSG = "", ERR_CODE = "";
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节�?
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator iter = rootElt.elementIterator("CONST_HEAD"); // 获取根节点下的子节点head
			// 遍历head节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				ERR_MSG = recordEle.elementTextTrim("ERR_MSG"); // 拿到head节点下的子节点�?
				ERR_CODE = recordEle.elementTextTrim("ERR_CODE");
				map.put("ERR_MSG", ERR_MSG);
				map.put("ERR_CODE", ERR_CODE);
			}
			if (ERR_CODE.equals("00")) {
				Iterator iterbody = rootElt.elementIterator("DATA_AREA"); // /获取根节点下的子节点body
				// 遍历body节点
				while (iterbody.hasNext()) {
					Element recordEless = (Element) iterbody.next();
					String bindCardNo = recordEless
							.elementTextTrim("bindCardNo");// 预约单号
					String customerName = recordEless
							.elementTextTrim("customerName");// 就诊时间
					String identityType = recordEless
							.elementTextTrim("identityType");
					String identityNo = recordEless
							.elementTextTrim("identityNo");
					String telNo = recordEless.elementTextTrim("telNo");
					map.put("bindCardNo", bindCardNo);
					map.put("customerName", customerName);
					map.put("identityType", identityType);
					map.put("identityNo", identityNo);
					map.put("telNo", telNo);
				}

			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}